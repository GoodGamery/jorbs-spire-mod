package stsjorbsmod.console;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stsjorbsmod.actions.GainMemoryClarityAction;
import stsjorbsmod.actions.RememberRandomNewMemoryAction;
import stsjorbsmod.actions.RememberSpecificMemoryAction;
import stsjorbsmod.powers.memories.AbstractMemoryPower;
import stsjorbsmod.util.MemoryPowerUtils;

import java.util.ArrayList;

public class MemoryRememberCommand extends ConsoleCommand {
    public MemoryRememberCommand() {
        maxExtraTokens = 1;
        minExtraTokens = 0;
        requiresPlayer = true;
        simpleCheck = true;
    }

    @Override
    public void execute(String[] tokens, int depth) {
        String optionalId = tokens.length > depth ? tokens[depth] : null;

        if (optionalId == null) {
            DevConsole.log("Remembering an random new memory (like Eye of the Storm)");
            AbstractDungeon.actionManager.addToBottom(new RememberRandomNewMemoryAction(AbstractDungeon.player, AbstractDungeon.player, false));
        } else {
            AbstractMemoryPower newMemory = MemoryPowerUtils.newMemoryByID(tokens[2], AbstractDungeon.player, AbstractDungeon.player, false);
            AbstractDungeon.actionManager.addToBottom(new RememberSpecificMemoryAction(AbstractDungeon.player, AbstractDungeon.player, newMemory));
        }
    }

    @Override
    public ArrayList<String> extraOptions(String[] tokens, int depth) {
        return MemoryPowerUtils.allPossibleMemoryIDs();
    }
}