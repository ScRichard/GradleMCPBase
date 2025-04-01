package dev.gothaj.values;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

@Getter
@Setter
public class Setting<T> {

    public static final Minecraft mc = Minecraft.getMinecraft();

    private T parent;

}
