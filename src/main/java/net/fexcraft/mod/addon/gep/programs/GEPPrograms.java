package net.fexcraft.mod.addon.gep.programs;

import net.fexcraft.lib.common.Static;
import net.fexcraft.mod.addon.gep.scripts.SmelteryScript;
import net.fexcraft.mod.fvtm.block.generated.MultiblockTickableTE;
import net.fexcraft.mod.fvtm.data.block.CraftBlockScript;
import net.fexcraft.mod.fvtm.data.block.MultiBlockData;
import net.fexcraft.mod.fvtm.data.inv.InvHandlerItem.StackEntry;
import net.fexcraft.mod.fvtm.model.ModelGroup;
import net.fexcraft.mod.fvtm.model.ModelRenderData;
import net.fexcraft.mod.fvtm.model.Program;
import org.lwjgl.opengl.GL11;

public class GEPPrograms {

	public static void register(){
		ModelGroup.PROGRAMS.add(new Program(){
			
			private float rotation;
			
			@Override
			public String id(){ return "gep:crusher_gears"; }
			
			@Override
			public void pre(ModelGroup list, ModelRenderData data){
				if(data.tile == null || data.cache == null) return;
				MultiBlockData multidata = ((MultiblockTickableTE)data.tile).getMultiBlockData();
				if(multidata != null && multidata.getScript() != null && ((CraftBlockScript)multidata.getScript()).getProcessed() > 0){
			    	rotation = data.cache.getValue("rot_state", 0f) + 0.1f;
			    	data.cache.setValue("rot_state", rotation > 360 ? rotation -= 360 : rotation);
			    	list.rotate(rotation, 2, false);
				}
				else rotation = 0;
			}
			
			@Override
			public void post(ModelGroup list, ModelRenderData data){
				if(rotation != 0) list.rotate(-rotation, 2, false);
			}
			
		});
		ModelGroup.PROGRAMS.add(new Program(){
			
			private int fullstate;
			
			@Override
			public String id(){ return "gep:crusher_fillstate"; }
			
			@Override
			public void pre(ModelGroup list, ModelRenderData data){
				if(data.tile == null || data.cache == null) return;
				MultiBlockData multidata = ((MultiblockTickableTE)data.tile).getMultiBlockData();
				if(multidata != null && multidata.getInventory("output") != null){
					fullstate = 0;
					for(StackEntry entry : multidata.getInventory("output").getStacks()){
						fullstate += entry.stacksize();
					}
			    	GL11.glTranslatef(0, -Static.sixteenth * fullstate, 0);
				}
			}
			
			@Override
			public void post(ModelGroup list, ModelRenderData data){
		    	GL11.glTranslatef(0, Static.sixteenth * fullstate, 0);
			}
			
		});
		ModelGroup.PROGRAMS.add(new SmelteryDoor(0));
	}
	
	public static class SmelteryDoor implements Program {
		
		private boolean wasopen;
		private int angle;

		public SmelteryDoor(int i){
			angle = i;
		}
		
		@Override
		public String id(){ return "gep:smeltery_doors"; }

		@Override
		public void pre(ModelGroup list, ModelRenderData data){
			if(data.tile == null) return;
			MultiBlockData multidata = ((MultiblockTickableTE)data.tile).getMultiBlockData();
			if(multidata != null && multidata.getScript() != null && ((SmelteryScript)multidata.getScript()).isOpen()){
				list.rotate(0, angle, 0, true);
				wasopen = true;
			}
		}

		@Override
		public void post(ModelGroup list, ModelRenderData data){
			if(wasopen){
				list.rotate(0, 0, 0, true);
				wasopen = false;
			}
		}
		
		@Override
		public Program parse(String[] args){
			return new SmelteryDoor(Integer.parseInt(args[0]));
		}
		
	}

}
