package mekanism.common.recipe.inputs;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasTank;
import mekanism.api.util.StackUtils;

import net.minecraft.item.ItemStack;

public class AdvancedMachineInput extends MachineInput<AdvancedMachineInput>
{
	public ItemStack itemStack;

	public Gas gasType;

	public AdvancedMachineInput(ItemStack item, Gas gas)
	{
		itemStack = item;
		gasType = gas;
	}

	@Override
	public AdvancedMachineInput copy()
	{
		return new AdvancedMachineInput(itemStack.copy(), gasType);
	}

	@Override
	public boolean isValid()
	{
		return itemStack != null && gasType != null;
	}

	public boolean useItem(ItemStack[] inventory, int index, boolean deplete)
	{
		if(StackUtils.contains(inventory[index], itemStack))
		{
			if(deplete)
			{
				inventory[index] = StackUtils.subtract(inventory[index], itemStack);
			}
			return true;
		}
		return false;
	}

	public boolean useSecondary(GasTank gasTank, int amountToUse, boolean deplete)
	{
		if(gasTank.getGasType() == gasType && gasTank.getStored() >= amountToUse)
		{
			gasTank.draw(amountToUse, deplete);
			return true;
		}
		return false;
	}

	public boolean matches(AdvancedMachineInput input)
	{
		return StackUtils.equalsWildcard(itemStack, input.itemStack) && input.itemStack.stackSize >= itemStack.stackSize;
	}

	@Override
	public int hashIngredients()
	{
		int hash = StackUtils.hashItemStack(itemStack) << 8 | gasType.getID();
		return hash;
	}

	@Override
	public boolean testEquality(AdvancedMachineInput other)
	{
		return StackUtils.equalsWildcardWithNBT(itemStack, other.itemStack) && gasType.getID() == other.gasType.getID();
	}

	@Override
	public boolean isInstance(Object other)
	{
		return other instanceof AdvancedMachineInput;
	}
}
