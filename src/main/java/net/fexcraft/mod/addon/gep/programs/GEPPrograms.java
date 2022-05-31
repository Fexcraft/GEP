package net.fexcraft.mod.addon.gep.programs;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.fexcraft.lib.common.Static;
import net.fexcraft.mod.addon.gep.scripts.SmelteryScript;
import net.fexcraft.mod.fvtm.block.generated.MultiblockTickableTE;
import net.fexcraft.mod.fvtm.data.block.CraftBlockScript;
import net.fexcraft.mod.fvtm.data.block.MultiBlockData;
import net.fexcraft.mod.fvtm.data.root.Model.ModelRenderData;
import net.fexcraft.mod.fvtm.model.ModelGroup;
import net.minecraft.item.ItemStack;

public class GEPPrograms {

	public static void register(){
		ModelGroup.PROGRAMS.add(new ModelGroup.Program(){
			
			private float rotation;
			
			@Override
			public String getId(){ return "gep:crusher_gears"; }
			
			@Override
			public void preRender(ModelGroup list, ModelRenderData data){
				if(data.tile == null || data.cache == null) return;
				MultiBlockData multidata = ((MultiblockTickableTE)data.tile).getMultiBlockData();
				if(multidata != null && multidata.getScript() != null && ((CraftBlockScript)multidata.getScript()).getProcessed() > 0){
			    	rotation = data.cache.getValue("rot_state", 0f) + 0.1f;
			    	data.cache.setValue("rot_state", rotation > 360 ? rotation -= 360 : rotation);
			    	list.rotateAxis(rotation, 2, false);
				}
				else rotation = 0;
			}
			
			@Override
			public void postRender(ModelGroup list, ModelRenderData data){
				if(rotation != 0) list.rotateAxis(-rotation, 2, false);
			}
			
		});
		ModelGroup.PROGRAMS.add(new ModelGroup.Program(){
			
			private int fullstate;
			
			@Override
			public String getId(){ return "gep:crusher_fillstate"; }
			
			@Override
			public void preRender(ModelGroup list, ModelRenderData data){
				if(data.tile == null || data.cache == null) return;
				MultiBlockData multidata = ((MultiblockTickableTE)data.tile).getMultiBlockData();
				if(multidata != null && multidata.getInventory("output") != null){
					List<ItemStack> stacks = multidata.getInventory("output");
					fullstate = 0;
					for(int i = 0; i < 16; i++){
						if(!stacks.get(i).isEmpty()) fullstate++;
					}
			    	GL11.glTranslatef(0, -Static.sixteenth * fullstate, 0);
				}
			}
			
			@Override
			public void postRender(ModelGroup list, ModelRenderData data){
		    	GL11.glTranslatef(0, Static.sixteenth * fullstate, 0);
			}
			
		});
		ModelGroup.PROGRAMS.add(new SmelteryDoor(0));
	}
	
	public static class SmelteryDoor implements ModelGroup.Program {
		
		private boolean wasopen;
		private int angle;

		public SmelteryDoor(int i){
			angle = i;
		}
		
		@Override
		public String getId(){ return "gep:smeltery_doors"; }

		@Override
		public void preRender(ModelGroup list, ModelRenderData data){
			if(data.tile == null) return;
			MultiBlockData multidata = ((MultiblockTickableTE)data.tile).getMultiBlockData();
			if(multidata != null && multidata.getScript() != null && ((SmelteryScript)multidata.getScript()).isOpen()){
				list.rotate(0, angle, 0, true);
				wasopen = true;
			}
		}

		@Override
		public void postRender(ModelGroup list, ModelRenderData data){
			if(wasopen){
				list.rotate(0, 0, 0, true);
				wasopen = false;
			}
		}
		
		@Override
		public ModelGroup.Program parse(String[] args){
			return new SmelteryDoor(Integer.parseInt(args[0]));
		}
		
	}

}
