package mods.eln.sixnode.electricaldigitaldisplay;

import mods.eln.sim.IProcess;

public class ElectricalDigitalDisplayProcess implements IProcess {
    ElectricalDigitalDisplayElement element;

    public ElectricalDigitalDisplayProcess(ElectricalDigitalDisplayElement element) {
        this.element = element;
    }

    @Override
    public void process(double time) {
        element.current = (float) element.input.getNormalized();
        if(element.current != element.last) {
            element.needPublish();
            element.last = element.current;
        }
        element.strobe = (float) element.strobeIn.getNormalized();
        if(element.strobe != element.strobeLast) {
            element.needPublish();
            element.strobeLast = element.strobe;
        }
        element.dots = (float) element.dotsIn.getNormalized();
        if(element.dots != element.dotsLast) {
            element.needPublish();
            element.dotsLast = element.dots;
        }
    }
}
