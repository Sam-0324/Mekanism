package mekanism.common.capabilities.resolver;

import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface ICapabilityResolver {

    List<Capability<?>> getSupportedCapabilities();

    <T> LazyOptional<T> resolve(Capability<T> capability, @Nullable Direction side);
}