package cursedflames.noincreasingrepaircost;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(NoIncreasingRepairCost.MODID)
public class NoIncreasingRepairCost {
	public static final String MODID = "noincreasingrepaircost";
	
//	public static final Logger logger = LogManager.getLogger();
	
	public NoIncreasingRepairCost() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent event) {
		// need this one too since MC doesn't fire AnvilRepairEvent when the
		// player shift-clicks apparently
		resetRepairValue(event.getLeft());
		resetRepairValue(event.getRight());
	}

	@SubscribeEvent
	public void onAnvilUpdate(AnvilRepairEvent event) {
		resetRepairValue(event.getItemResult());
	}

	private void resetRepairValue(ItemStack stack) {
		if (!stack.isEmpty() && stack.hasTag()) {
			stack.getTag().remove("RepairCost");
		}
	}
}
