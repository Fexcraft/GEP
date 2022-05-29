//FMT-Marker FVTM-1.5
package net.fexcraft.mod.addon.gep.models.block;

import net.fexcraft.lib.mc.api.registry.fModel;
import net.fexcraft.lib.tmt.ModelRendererTurbo;
import net.fexcraft.lib.tmt.RotationOrder;
import net.fexcraft.mod.fvtm.model.BlockModel;
import net.fexcraft.mod.fvtm.model.ConditionalPrograms;
import net.fexcraft.mod.fvtm.model.DefaultPrograms;
import net.fexcraft.mod.fvtm.model.ModelGroup;

/** This file was exported via the FVTM Exporter v1.5 of<br>
 *  FMT (Fex's Modelling Toolbox) v.2.6.5 &copy; 2021 - Fexcraft.net<br>
 *  All rights reserved. For this Model's License contact the Author/Creator.
 */
@fModel(registryname = "gep:models/block/simple_switch_fork3")
public class SimpleSwitchFork3Model extends BlockModel {

	public SimpleSwitchFork3Model(){
		super(); textureX = 32; textureY = 32;
		this.addToCreators("Ferdinand (FEX___96)");
		//
		ModelGroup base = new ModelGroup("base");
		base.add(new ModelRendererTurbo(base, 0, 6, textureX, textureY).addCylinder(0, 0, 0, 2, 2, 16, 1, 1, 1, null)
			.setRotationPoint(0, -1, 1).setRotationAngle(0, 0, -90)
		);
		base.add(new ModelRendererTurbo(base, 0, 0, textureX, textureY)
			.addShapeBox(0, 0, 0, 8, 1, 4, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-4, -1, 0).setRotationAngle(0, 0, 0)
		);
		base.add(new ModelRendererTurbo(base, 0, 22, textureX, textureY).addCylinder(0, 0, 0, 2, 2, 16, 1, 1, 1, null)
			.setRotationPoint(0, -1, -3).setRotationAngle(0, 0, -90)
		);
		base.add(new ModelRendererTurbo(base, 0, 16, textureX, textureY)
			.addShapeBox(0, 0, 0, 8, 1, 4, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, -0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
			.setRotationPoint(-4, -1, -4).setRotationAngle(0, 0, 0)
		);
		this.groups.add(base);
		//
		ModelGroup lever0 = new ModelGroup("lever0");
		lever0.add(new ModelRendererTurbo(lever0, 9, 6, textureX, textureY).addBox(-0.25f, -8, -0.25f, 0.5f, 8, 0.5f)
			.setRotationPoint(0, -1, 2).setRotationAngle(0, 0, 16)
		);
		lever0.addProgram(new ConditionalPrograms.SwitchFork3State(0)
			.add(new DefaultPrograms.RotationSetter(2, 20, 0, true)));
		this.groups.add(lever0);
		//
		ModelGroup sign0 = new ModelGroup("sign0");
		sign0.add(new ModelRendererTurbo(sign0, 14, 6, textureX, textureY).addBox(-0.2f, -11, -1.5f, 0.4f, 3, 3)
			.setRotationPoint(0, -1, 2).setRotationAngle(0, 0, 16).setRotationOrder(RotationOrder.ZYX)
		);
		sign0.add(new ModelRendererTurbo(sign0, 23, 6, textureX, textureY).addBox(-1.5f, -11, -0.2f, 3, 3, 0.4f)
			.setRotationPoint(0, -1, 2).setRotationAngle(0, 0, 16).setRotationOrder(RotationOrder.ZYX)
		);
		sign0.addPrograms(new ConditionalPrograms.SwitchFork3State(0)
			.add(new DefaultPrograms.RotationSetter(2, 20, 0, true))
			.add(new DefaultPrograms.RotationSetter(1, 90, 0, true)));
		this.groups.add(sign0);
		//
		ModelGroup lever1 = new ModelGroup("lever1");
		lever1.add(new ModelRendererTurbo(lever1, 9, 22, textureX, textureY).addBox(-0.25f, -8, -0.25f, 0.5f, 8, 0.5f)
			.setRotationPoint(0, -1, -2).setRotationAngle(0, 0, -16)
		);
		lever1.addProgram(new ConditionalPrograms.SwitchFork3State(2)
			.add(new DefaultPrograms.RotationSetter(2, -20, 0, true)));
		this.groups.add(lever1);
		//
		ModelGroup sign1 = new ModelGroup("sign1");
		sign1.add(new ModelRendererTurbo(sign1, 14, 22, textureX, textureY).addBox(-0.2f, -11, -1.5f, 0.4f, 3, 3)
			.setRotationPoint(0, -1, -2).setRotationAngle(0, 0, -16).setRotationOrder(RotationOrder.ZYX)
		);
		sign1.add(new ModelRendererTurbo(sign1, 23, 22, textureX, textureY).addBox(-1.5f, -11, -0.2f, 3, 3, 0.4f)
			.setRotationPoint(0, -1, -2).setRotationAngle(0, 0, -16).setRotationOrder(RotationOrder.ZYX)
		);
		sign1.addPrograms(new ConditionalPrograms.SwitchFork3State(2)
			.add(new DefaultPrograms.RotationSetter(2, -20, 0, true))
			.add(new DefaultPrograms.RotationSetter(1, 90, 0, true)));
		this.groups.add(sign1);
		//
	}

}
