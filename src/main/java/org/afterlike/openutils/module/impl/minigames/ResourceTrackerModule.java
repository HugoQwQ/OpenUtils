package org.afterlike.openutils.module.impl.minigames;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.afterlike.openutils.event.api.EventPhase;
import org.afterlike.openutils.event.handler.EventHandler;
import org.afterlike.openutils.event.impl.GameTickEvent;
import org.afterlike.openutils.module.api.Module;
import org.afterlike.openutils.module.api.ModuleCategory;
import org.afterlike.openutils.module.api.setting.impl.BooleanSetting;
import org.afterlike.openutils.module.api.setting.impl.DescriptionSetting;
import org.afterlike.openutils.util.client.ClientUtil;
import org.afterlike.openutils.util.game.GameModeUtil;

public class ResourceTrackerModule extends Module {
	public static DescriptionSetting description;
	public static BooleanSetting pingSound;
	public static BooleanSetting trackIron;
	public static BooleanSetting trackGold;
	public static BooleanSetting trackDiamonds;
	public static BooleanSetting trackEmeralds;
	public ResourceTrackerModule() {
		super("Resource Tracker", ModuleCategory.MINIGAMES);
		this.registerSetting(
				description = new DescriptionSetting("Tracks resources in your inventory"));
		this.registerSetting(trackIron = new BooleanSetting("Track Iron", false));
		this.registerSetting(trackGold = new BooleanSetting("Track Gold", false));
		this.registerSetting(trackDiamonds = new BooleanSetting("Track Diamonds", true));
		this.registerSetting(trackEmeralds = new BooleanSetting("Track Emeralds", true));
	}
	private static final Map<Item, Integer> lastCounts = new HashMap<>();
	@EventHandler
	public void onTick(GameTickEvent event) {
		if (event.getPhase() != EventPhase.POST)
			return;
		if (!GameModeUtil.onHypixel())
			return;
		if (GameModeUtil.getBedWarsStatus() != 3)
			return;
		if (!ClientUtil.notNull())
			return;
		Map<Item, Integer> current = new HashMap<>();
		initCounts(current);
		for (ItemStack stack : mc.thePlayer.inventory.mainInventory) {
			if (stack == null)
				continue;
			Item item = stack.getItem();
			if (!current.containsKey(item))
				continue;
			current.put(item, current.get(item) + stack.stackSize);
		}
		handleChanges(current);
		lastCounts.clear();
		lastCounts.putAll(current);
	}

	private void handleChanges(Map<Item, Integer> current) {
		for (Map.Entry<Item, Integer> entry : current.entrySet()) {
			Item item = entry.getKey();
			int newCount = entry.getValue();
			int oldCount = lastCounts.getOrDefault(item, 0);
			if (newCount == oldCount)
				continue;
			if (!isTracking(item))
				continue;
			boolean gained = newCount > oldCount;
			String prefix = gained
					? EnumChatFormatting.GREEN + "[+] "
					: EnumChatFormatting.RED + "[-] ";
			ClientUtil.sendMessage(prefix + getItemDisplayName(item) + EnumChatFormatting.DARK_GRAY
					+ " (" + newCount + ")");
			// TODO: add sound utils
			// if (pingSound.getValue()) {
			// SoundUtil.playPing();
			// }
		}
	}

	private boolean isTracking(Item item) {
		if (item == Items.iron_ingot)
			return trackIron.getValue();
		if (item == Items.gold_ingot)
			return trackGold.getValue();
		if (item == Items.diamond)
			return trackDiamonds.getValue();
		if (item == Items.emerald)
			return trackEmeralds.getValue();
		return false;
	}

	private String getItemDisplayName(Item item) {
		if (item == Items.iron_ingot)
			return EnumChatFormatting.WHITE + "Iron";
		if (item == Items.gold_ingot)
			return EnumChatFormatting.GOLD + "Gold";
		if (item == Items.diamond)
			return EnumChatFormatting.AQUA + "Diamond";
		if (item == Items.emerald)
			return EnumChatFormatting.DARK_GREEN + "Emerald";
		return "Unknown";
	}

	private static void initCounts(Map<Item, Integer> map) {
		map.put(Items.iron_ingot, 0);
		map.put(Items.gold_ingot, 0);
		map.put(Items.diamond, 0);
		map.put(Items.emerald, 0);
	}

	private static void resetCounts() {
		lastCounts.clear();
		initCounts(lastCounts);
	}

	@Override
	public void onDisable() {
		resetCounts();
	}
}
