package mekanism.common.tile.interfaces;

import mekanism.api.text.EnumColor;
import mekanism.common.content.transporter.TransporterStack;
import mekanism.common.lib.inventory.TransitRequest;
import mekanism.common.lib.inventory.TransitRequest.TransitResponse;
import mekanism.common.lib.transmitter.IBlockableConnection;
import mekanism.common.lib.transmitter.IGridTransmitter;
import mekanism.common.tile.TileEntityLogisticalSorter;
import mekanism.common.content.transmitter.grid.InventoryNetwork;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;

public interface ILogisticalTransporter extends IGridTransmitter<TileEntity, InventoryNetwork, Void>, IBlockableConnection {

    TransitResponse insert(TileEntity outputter, TransitRequest request, EnumColor color, boolean doEmit, int min);

    TransitResponse insertRR(TileEntityLogisticalSorter outputter, TransitRequest request, EnumColor color, boolean doEmit, int min);

    void entityEntering(TransporterStack stack, int progress);

    EnumColor getColor();

    void setColor(EnumColor c);

    boolean canEmitTo(TileEntity tile, Direction side);

    boolean canReceiveFrom(TileEntity tile, Direction side);

    double getCost();
}