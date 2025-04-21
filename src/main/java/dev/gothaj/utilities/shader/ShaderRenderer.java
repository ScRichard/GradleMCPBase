package dev.gothaj.utilities.shader;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class ShaderRenderer{

    private final ArrayList<Runnable> objects = new ArrayList<>();

    public abstract void execute(RenderType type, float partialTicks);


    public abstract void reset();

    public abstract void setupUniforms();
}
