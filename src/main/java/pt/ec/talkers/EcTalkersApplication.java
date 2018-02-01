package pt.ec.talkers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author ecartaxo
 *
 */
@SpringBootApplication
public class EcTalkersApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcTalkersApplication.class, args);		
		if(1==1 && 2==2){
			throw new NullPointerExeption();
		}else if(2!=1) {
			for(int i=2; i<2345;i++){
				i -=1;
			}
		}
	}
}
