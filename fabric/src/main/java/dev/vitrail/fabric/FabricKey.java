package dev.vitrail.fabric;

import dev.vitrail.platform.EngineStages;
import dev.vitrail.screen.SettingsKey;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

final class FabricKey {
	private FabricKey() {}

	static void register() {
		try {
			Class<?> kmh = Class.forName("net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper");
			kmh.getMethod("registerKeyMapping", Object.class).invoke(null, SettingsKey.OPEN);
		} catch (Throwable ignored) {
			// На 1.21.10 клавиша подхватывается встроенным меню настроек
		}
		try {
			ClientTickEvents.START_CLIENT_TICK.register(_ -> EngineStages.clientTickStart());
			ClientTickEvents.END_CLIENT_TICK.register(_ -> EngineStages.clientTick());
		} catch (Throwable ignored) {}
	}
}
