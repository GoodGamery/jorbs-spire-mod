package stsjorbsmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import stsjorbsmod.powers.AbstractMemoryPower;


// This is like ApplyPowerAction, but with the additional effect of removing other non-clarified memories
public class RememberSpecificMemoryAction extends AbstractGameAction  {
    private AbstractMemoryPower memoryToRemember;

    public RememberSpecificMemoryAction(AbstractCreature target, AbstractCreature source, AbstractMemoryPower memoryToRemember) {
        this.setValues(target, source);
        this.memoryToRemember = memoryToRemember;
    }

    public void update() {
        for (AbstractPower oldPower : this.source.powers) {
            if (oldPower instanceof AbstractMemoryPower) {
                AbstractMemoryPower oldMemory = (AbstractMemoryPower) oldPower;
                if (!oldMemory.isClarified) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, source, oldMemory));
                }
            }
        }

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, memoryToRemember));
    }
}