package net.fexcraft.mod.addon.gep;

import net.fexcraft.mod.addon.gep.programs.GEPPrograms;
import net.fexcraft.mod.fvtm.data.addon.AddonClass;
import net.minecraftforge.fml.common.Mod;

@AddonClass(registryname = "fvtm:gep")
@Mod(modid = "gep", name = "General Essentials Pack", version = "2.5.0", useMetadata = true, dependencies = "required-after:fcl;required-after:fvtm")
public class GeneralEssentialsPack {
	
	public GeneralEssentialsPack(){
		GEPPrograms.register();
	}
	
}
