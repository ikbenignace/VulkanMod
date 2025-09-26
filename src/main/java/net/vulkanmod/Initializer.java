package net.vulkanmod;

import net.fabricmc.api.ClientModInitializer;
// DISABLED: Fabric Renderer API integration temporarily disabled due to API restructuring in 1.21.6+
// import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.loader.api.FabricLoader;
import net.vulkanmod.config.Config;
import net.vulkanmod.config.Platform;
import net.vulkanmod.config.video.VideoModeManager;
// DISABLED: Fabric Renderer integration disabled for 1.21.8 migration
// import net.vulkanmod.render.chunk.build.frapi.VulkanModRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

public class Initializer implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("VulkanMod");

	private static String VERSION;
	public static Config CONFIG;

	@Override
	public void onInitializeClient() {

		VERSION = FabricLoader.getInstance()
				.getModContainer("vulkanmod")
				.get()
				.getMetadata()
				.getVersion().getFriendlyString();

		LOGGER.info("== VulkanMod ==");

		Platform.init();
		VideoModeManager.init();

		var configPath = FabricLoader.getInstance()
				.getConfigDir()
				.resolve("vulkanmod_settings.json");

		CONFIG = loadConfig(configPath);

		// DISABLED: Fabric Renderer API integration temporarily disabled due to API restructuring in 1.21.6+
		// The entire net.fabricmc.fabric.api.renderer.v1 package appears to have been removed or significantly restructured.
		// VulkanMod's core rendering functionality remains unaffected.
		
		LOGGER.info("VulkanMod: Fabric Renderer integration disabled for 1.21.8 - using core Vulkan rendering");
		
		// TODO: Future implementation should integrate with direct Minecraft rendering APIs:
		// - net.minecraft.client.renderer.block.BlockRenderDispatcher
		// - net.minecraft.client.renderer.MultiBufferSource  
		// - net.minecraft.client.renderer.RenderType
	}

	private static Config loadConfig(Path path) {
		Config config = Config.load(path);

		if(config == null) {
			config = new Config();
			config.write();
		}

		return config;
	}

	public static String getVersion() {
		return VERSION;
	}
}
