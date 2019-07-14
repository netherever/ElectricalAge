package mods.eln.sim.nbt

import mods.eln.misc.FunctionTable
import mods.eln.misc.INBTTReady
import mods.eln.node.NodeBase
import mods.eln.sim.BatteryProcess
import mods.eln.sim.BatterySlowProcess
import mods.eln.sim.IProcess
import mods.eln.sim.ThermalLoad
import mods.eln.sim.mna.component.VoltageSource
import mods.eln.sim.mna.state.VoltageState
import net.minecraft.nbt.NBTTagCompound

class NbtBatteryProcess(positiveLoad: VoltageState, negativeLoad: VoltageState, voltageFunction: FunctionTable, IMax: Double, voltageSource: VoltageSource, thermalLoad: ThermalLoad) : BatteryProcess(positiveLoad, negativeLoad, voltageFunction, IMax, voltageSource, thermalLoad), INBTTReady {

    override fun readFromNBT(nbttagcompound: NBTTagCompound, str: String) {
        Q = nbttagcompound.getDouble(str + "NBP" + "Q")
        if (java.lang.Double.isNaN(Q)) Q = 0.0
        life = nbttagcompound.getDouble(str + "NBP" + "life")
        if (java.lang.Double.isNaN(life)) life = 1.0
    }

    override fun writeToNBT(nbttagcompound: NBTTagCompound, str: String) {
        nbttagcompound.setDouble(str + "NBP" + "Q", Q)
        nbttagcompound.setDouble(str + "NBP" + "life", life)
    }
}

class NbtBatterySlowProcess(val node: NodeBase, batteryProcess: NbtBatteryProcess): BatterySlowProcess(batteryProcess) {
    val explosionRadius = 2.0

    override fun destroy() {
        node.physicalSelfDestruction(explosionRadius.toFloat())
    }
}