package softuni.exam.service.contracts;


import softuni.exam.models.entity.Town;

//ToDo - Before start App implement this Service and set areImported to return false
public interface TownService {

    boolean areImported();

    String readTownsFileContent() ;
	
	String importTowns() ;

    Town getTownByName(String townName);
}
