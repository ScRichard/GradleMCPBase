package dev.gothaj.utilities.shader.initlaizers;

import dev.gothaj.utilities.shader.RenderType;

public interface ShaderRendererInitializer {

    void execute(RenderType type, float partialTicks);

    void reset();

    void setupUniforms();

}
