package softuni.exam.service.contracts;


import softuni.exam.models.entity.Plane;

public interface PlaneService {

    boolean areImported();

    String readPlanesFileContent() ;
	
	String importPlanes();

    Plane getPlaneBySerialNumber(String serialNumber);
}
