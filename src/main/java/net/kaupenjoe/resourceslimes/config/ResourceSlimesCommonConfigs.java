package net.kaupenjoe.resourceslimes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ResourceSlimesCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> CITRINE_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> CITRINE_ORE_VEINS_TRIANGLE_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> CITRINE_ORE_VEINS_UNIFORM_PER_CHUNK;

    public static final ForgeConfigSpec.ConfigValue<Integer> ZIRCON_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ZIRCON_ORE_VEINS_TRIANGLE_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> ZIRCON_ORE_VEINS_UNIFORM_PER_CHUNK;

    public static final ForgeConfigSpec.ConfigValue<Integer> TANZANITE_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> TANZANITE_ORE_VEINS_TRIANGLE_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> TANZANITE_ORE_VEINS_MOUNTAIN_PER_CHUNK;

    public static final ForgeConfigSpec.ConfigValue<Integer> BLACK_OPAL_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> BLACK_OPAL_ORE_VEINS_PER_CHUNK;

    public static final ForgeConfigSpec.ConfigValue<Integer> END_BLACK_OPAL_ORE_VEIN_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> END_BLACK_OPAL_ORE_VEINS_PER_CHUNK;


    static {
        BUILDER.push("Citrine Ore Configs");

        CITRINE_ORE_VEIN_SIZE = BUILDER.comment("How many Citrine Ore Blocks spawn in one Vein!")
                .defineInRange("Citrine Ore Vein Size",6, 3, 16);
        CITRINE_ORE_VEINS_TRIANGLE_PER_CHUNK = BUILDER.comment("How many Citrine Ore Veins spawn per chunk in its Triangle World Gen!")
                .define("Citrine Ore Veins Per Chunk Triangle",6);
        CITRINE_ORE_VEINS_UNIFORM_PER_CHUNK = BUILDER.comment("How many Citrine Ore Veins spawn per chunk in its Uniform World Gen!")
                .define("Citrine Ore Veins Per Chunk Uniform",4);

        BUILDER.pop();
        BUILDER.push("Zircon Ore Configs");

        ZIRCON_ORE_VEIN_SIZE = BUILDER.comment("How many Zircon Ore Blocks spawn in one Vein!")
                .defineInRange("Zircon Ore Vein Size",4, 3, 16);
        ZIRCON_ORE_VEINS_TRIANGLE_PER_CHUNK = BUILDER.comment("How many Zircon Ore Veins spawn per chunk in its Triangle World Gen!")
                .define("Zircon Ore Veins Per Chunk Triangle",6);
        ZIRCON_ORE_VEINS_UNIFORM_PER_CHUNK = BUILDER.comment("How many Zircon Ore Veins spawn per chunk in its Uniform World Gen!")
                .define("Zircon Ore Veins Per Chunk Uniform",2);

        BUILDER.pop();
        BUILDER.push("Tanzanite Ore Configs");

        TANZANITE_ORE_VEIN_SIZE = BUILDER.comment("How many Tanzanite Ore Blocks spawn in one Vein!")
                .defineInRange("Tanzanite Ore Vein Size",7, 3, 16);
        TANZANITE_ORE_VEINS_TRIANGLE_PER_CHUNK = BUILDER.comment("How many Tanzanite Ore Veins spawn per chunk in its Triangle World Gen!")
                .define("Tanzanite Ore Veins Per Chunk Triangle",3);
        TANZANITE_ORE_VEINS_MOUNTAIN_PER_CHUNK = BUILDER.comment("How many Tanzanite Ore Veins spawn per chunk in its Uniform World Gen!")
                .define("Tanzanite Ore Veins Per Chunk Mountain",3);

        BUILDER.pop();
        BUILDER.push("Black Opal Ore Configs");

        BLACK_OPAL_ORE_VEIN_SIZE = BUILDER.comment("How many Black Opal Ore Blocks spawn in one Vein!")
                .defineInRange("Black Opal Ore Vein Size",4, 3, 16);
        BLACK_OPAL_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Black Opal Ore Veins spawn per chunk!")
                .define("Black Opal Ore Veins Per Chunk", 2);

        BUILDER.pop();
        BUILDER.push("End Black Opal Ore Configs");

        END_BLACK_OPAL_ORE_VEIN_SIZE = BUILDER.comment("How many End Black Opal Ore Blocks spawn in one Vein!")
                .defineInRange("End Black Opal Ore Vein Size",5, 3, 16);
        END_BLACK_OPAL_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many End Black Opal Ore Veins spawn per chunk!")
                .define("End Black Opal Ore Veins Per Chunk", 5);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
