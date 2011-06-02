package utilidades;
import java.text.SimpleDateFormat;
import java.util.*;
public class Fechavo{
	
  Calendar c=Calendar.getInstance();
  
  	int dia=c.get(Calendar.DATE);
  	int mes=c.get(Calendar.MONTH)+1;
  	int ao=c.get(Calendar.YEAR); 
  	String dias[]={"Lun","Mar","Mie","Jue","Vie","Sab","Dom"};
  	String meses[]={"Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
  public String FechaActual(){
  	return ""+dia+"/"+mes+"/"+ao;
  }
   public String FechaActual2(){
  	return ""+ao+"/"+mes+"/"+dia;
  }
  public String fechaSiguiente(String fecha_in){
  	StringTokenizer f=new StringTokenizer(fecha_in,"/");
    int aoi=Integer.parseInt(f.nextToken() );
    int mesi=Integer.parseInt(f.nextToken() );
  	int diai=Integer.parseInt(f.nextToken());
  	
  	
  	if(aoi%4==0){
  		if(mesi!=12){
  			if(mesi==2){
  				if(diai+1>29){
  					mesi++;
  					diai=1;
  				}else
  					diai++;
  			}else{
  				if(mesi==1|mesi==3|mesi==5|mesi==7|mesi==8|mesi==10){
  					if(diai+1>31){
  						mesi++;
  						diai=1;
  					}else
  						diai++;
  				}else{
  					if(diai+1>30){
  						mesi++;
  						diai=1;
  					}else
  						diai++;
  				}		
  			}		
  		}else{
  			if(diai+1>31){
  				aoi++;
  				mesi=1;
  				diai=1;
  			}else
  				diai++;
  		}
  	}else{
  		if(mesi!=12){
  			if(mesi==2){
  				if(diai+1>28){
  					mesi++;
  					diai=1;
  				}else
  					diai++;
  			}else{
  				if(mesi==1|mesi==3|mesi==5|mesi==7|mesi==8|mesi==10){
  					if(diai+1>31){
  						mesi++;
  						diai=1;
  					}else
  						diai++;
  				}else{
  					if(diai+1>30){
  						mesi++;
  						diai=1;
  					}else
  						diai++;
  				}
  			}		
  		}else{
  			if(diai+1>31){
  				aoi++;
  				mesi=1;
  				diai=1;
  			}else
  				diai++;
  		}
  	}
  	return ""+aoi+"/"+mesi+"/"+diai;
  }	
  
  public boolean esMayor(String f1, String f2){
  	StringTokenizer f=new StringTokenizer(f1+"/"+f2,"/");
  	
  	int ao1=Integer.parseInt(f.nextToken());
    int mes1=Integer.parseInt(f.nextToken());
  	int dia1=Integer.parseInt(f.nextToken());
  	int ao2=Integer.parseInt(f.nextToken());
  	int mes2=Integer.parseInt(f.nextToken());
  	int dia2=Integer.parseInt(f.nextToken());
  	boolean sw=false;
  	if(ao1==ao2)
  		if(mes1==mes2)
  			if(dia1>dia2)
  				sw=true;
  			else
  				sw=false;
  		else
  			if(mes1>mes2)
  				sw=true;
  			else
  				sw=false;
  	else
  		if(ao1>ao2)			
  			sw=true;
  return sw;
  }
	
  public String Fechadevolucion(int d){
  		String fecha=FechaActual();
  		for(int i=0;i<d;i++){
  			fecha=fechaSiguiente(fecha);
  		}
  			return fecha;
  }
  public int diasDeDiferencia(String f1,String f2){
    StringTokenizer st=new StringTokenizer(f1+"/"+f2,"/");

  	int ao1=Integer.parseInt(st.nextToken());
    int mes1=Integer.parseInt(st.nextToken());
  	int dia1=Integer.parseInt(st.nextToken());
  	int ao2=Integer.parseInt(st.nextToken());
  	int mes2=Integer.parseInt(st.nextToken());
  	int dia2=Integer.parseInt(st.nextToken());
    f1=ao1+"/"+mes1+"/"+dia1;
    f2=ao2+"/"+mes2+"/"+dia2;
  	String f, fm;
  	int i=0;
  	if(esMayor(f1,f2)){
  		f=f2;
  		fm=f1;
  	}else{
  		f=f1;
  		fm=f2;
  	}
  		
  	while(!f.equals(fm)){
           // System.out.println(fechaSiguiente(f1));
  			f=fechaSiguiente(f);
  			i++;
  		}
  		return i;
  }
  
  public int DiaMulta(int diad,int mesd, int aod){//me da los dias que se ha pasado el alquiler
  	String f1=FechaActual();
	String f2=""+diad+"/"+mesd+"/"+aod;
  	int i=0;
  	if(esMayor(f1,f2)){

  		while(!f2.equals(f1)){
              
  			f2=fechaSiguiente(f2);
  			i++;
  		}
  		}
  		return i;
  		
  	}

  public int DiaMulta(String fecha){//me da los dias que se ha pasado el alquiler

    StringTokenizer st=new StringTokenizer(fecha,"/");
    int aod=Integer.parseInt(st.nextToken());
    int mesd=Integer.parseInt(st.nextToken());
    int diad=Integer.parseInt(st.nextToken());
    
  	

  	String f1=FechaActual2();
	String f2=""+aod+"/"+mesd+"/"+diad;
  //  System.out.println(f1+" "+f2);
  	int i=0;
  	if(esMayor(f1,f2)){

  		while(!f2.equals(f1)){
         //   System.out.println(f1+" "+f2);
  			f2=fechaSiguiente(f2);
  			i++;
  		}
  		}
  		return i;

  	}

  public String traducir(String d){
  		if(d.equals("Mon"))
  			d="Lun";
  		else if(d.equals("Tue"))
  			d="Mar";
  		else if(d.equals("Wed"))
  			d="Mie";
  		else if(d.equals("Thu") )
  			d="Jue";
  		else if(d.equals("Fri") )
  			d="Vie";
  		else if(d.equals("Sat"))
  			d="Sab";
  		else if(d.equals("Sun"))
  			d="Dom";
  		return d;
  }

    public static String traducirmeses(String d){
  		if(d.equals("January"))
  			d="Enero";
  		else if(d.equals("February"))
  			d="Febrero";
  		else if(d.equals("March"))
  			d="Marzo";
  		else if(d.equals("April") )
  			d="Abril";
  		else if(d.equals("May") )
  			d="Mayo";
  		else if(d.equals("June"))
  			d="Junio";
  		else if(d.equals("July"))
  			d="Julio";
        else if(d.equals("August"))
  			d="Agosto";
        else if(d.equals("September"))
  			d="Septiembre";
        else if(d.equals("October"))
  			d="Octubre";
        else if(d.equals("Noviembre"))
  			d="Noviembre";
        else if(d.equals("December"))
  			d="Diciembre";
  		return d;
  }

  public String[] ultimosDias(){
  	String fecha=(new Date().toString());
  	String d[]=new String[7];
  	fecha=traducir(fecha.substring(0,3));
  	int j;
  		for(j=0;j<d.length;j++){//busca la posicion del dia
  			if(fecha.equals(dias[j]))
  				break;
  		}
  			System.out.println(j);
  	for(int i=dias.length-1;i>=0;i--){	
  		d[i]=dias[j];
  		if(j==0)
  			j=dias.length;
  		j--;		
  	}
  	return d;
  }

  public static Date convertirFecha(String fecha){
      java.util.StringTokenizer st=new StringTokenizer(fecha,"-");
      Calendar calendar = Calendar.getInstance();

     int ano=Integer.parseInt(st.nextToken());
      int mes=Integer.parseInt(st.nextToken());
      int dia=Integer.parseInt(st.nextToken());


    calendar.set(Calendar.YEAR, ano);
    calendar.set(Calendar.MARCH, mes-1);
    calendar.set(Calendar.DATE, dia);

      return calendar.getTime();

  }

 public static void main( String arg[]){

  SimpleDateFormat formateador = new SimpleDateFormat("MMMM",new Locale("es_ES"));

  System.out.println(Fechavo.traducirmeses(formateador.format(new Date())));
 }
}