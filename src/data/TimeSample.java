package data;


import graph.VarValue;

import java.util.ArrayList;

public class TimeSample {
	private int time;
	private ArrayList<Sample> smp= new ArrayList<Sample>();
	
	protected TimeSample(int time){
		this.time=time;
	}
	
	protected void add(Sample smp){
		this.smp.add(smp);
	}
	
	protected Sample get(int pos){
		return smp.get(pos);
	}

	@Override
	public String toString() {
		return "TimeSample [time=" + time + ", smp=" + smp + "]";
	}
	//Adicionar funções ao UML 
	private void addValue(VarValue v, int index){
		this.get(index).add(v.value());
	}
				
	protected TimeSample result(int index, int num){
		TimeSample aux= new TimeSample(time);
		for (int linha = 0; linha < smp.size(); linha++) {
			if(smp.get(index).get(linha).value()==num){
				for (int var = 0; var < smp.size(); var++) {
					aux.addValue(smp.get(var).get(linha), var);
				}
			}
		}
		return aux;
	}
	
	protected int count(){
		return get(0).size();
	}
	
	
}
