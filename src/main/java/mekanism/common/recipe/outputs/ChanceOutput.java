package mekanism.common.recipe.outputs;

import java.util.Random;

import mekanism.api.util.StackUtils;

import net.minecraft.item.ItemStack;

public class ChanceOutput extends MachineOutput<ChanceOutput>
{
	private static Random rand = new Random();

	public ItemStack primaryOutput;

	public ItemStack secondaryOutput;

	public double secondaryChance;

	public ChanceOutput(ItemStack primary, ItemStack secondary, double chance)
	{
		primaryOutput = primary;
		secondaryOutput = secondary;
		secondaryChance = chance;
	}

	public ChanceOutput(ItemStack primary)
	{
		primaryOutput = primary;
	}

	public boolean checkSecondary()
	{
		return rand.nextDouble() <= secondaryChance;
	}

	public boolean hasPrimary()
	{
		return primaryOutput != null;
	}

	public boolean hasSecondary()
	{
		return secondaryOutput != null;
	}

	public boolean applyOutputs(ItemStack[] inventory, int primaryIndex, int secondaryIndex, boolean doEmit)
	{
		if(hasPrimary())
		{
			if(inventory[primaryIndex] == null)
			{
				if(doEmit)
				{
					inventory[primaryIndex] = primaryOutput.copy();
				}
				return true;
			} else if(inventory[primaryIndex].isItemEqual(primaryOutput) && inventory[primaryIndex].stackSize + primaryOutput.stackSize <= inventory[primaryIndex].getMaxStackSize())
			{
				if(doEmit)
				{
					inventory[primaryIndex].stackSize += primaryOutput.stackSize;
				}
				return true;
			}
			return false;
		}

		if(hasSecondary() && (!doEmit || checkSecondary()))
		{
			if(inventory[secondaryIndex] == null)
			{
				if(doEmit)
				{
					inventory[secondaryIndex] = secondaryOutput.copy();
				}
				return true;
			} else if(inventory[secondaryIndex].isItemEqual(secondaryOutput) && inventory[secondaryIndex].stackSize + primaryOutput.stackSize <= inventory[secondaryIndex].getMaxStackSize())
			{
				if(doEmit)
				{
					inventory[secondaryIndex].stackSize += secondaryOutput.stackSize;
				}
				return true;
			}
			return false;
		}

		return true;
	}

	public ChanceOutput copy()
	{
		return new ChanceOutput(StackUtils.copy(primaryOutput), StackUtils.copy(secondaryOutput), secondaryChance);
	}
}
