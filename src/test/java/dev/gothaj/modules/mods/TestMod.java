package dev.gothaj.modules.mods;

import dev.gothaj.features.modules.Mod;
import dev.gothaj.features.modules.anotations.ModRegister;
import dev.gothaj.values.impl.BooleanValue;

@ModRegister(name="ExampleTest")
public class TestMod extends Mod {

    public BooleanValue value = new BooleanValue("bname", true, null, this);

}
