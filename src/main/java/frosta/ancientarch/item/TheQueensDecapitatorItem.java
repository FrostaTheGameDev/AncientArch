package frosta.ancientarch.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheQueensDecapitatorItem extends AxeItem {
    public TheQueensDecapitatorItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String[] strings = Text.translatable("item.ancient-arch.the_queens_decapitator.lore").getString().split("\n");
        for (String s : strings) {
            tooltip.add(Text.literal(s).formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }
}