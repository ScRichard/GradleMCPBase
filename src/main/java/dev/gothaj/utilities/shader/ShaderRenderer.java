package dev.gothaj.utilities.shader;

import dev.gothaj.utilities.shader.initlaizers.ShaderRendererInitializer;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ShaderRenderer implements ShaderRendererInitializer {

    private final ArrayList<Runnable> objects = new ArrayList<>();

    @Override
    public void execute(RenderType type, float partialTicks) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void setupUniforms() {

    }
}
