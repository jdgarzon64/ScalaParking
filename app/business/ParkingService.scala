package business

import dtos.VehicleDTO
import javax.inject.{Inject, Singleton}
import play.api.mvc.Result

import scala.concurrent.{ExecutionContext, Future}
import models.Vehicle
import repository.IVehicleRepository

class ParkingService @Inject()
(iVehicleRepository: IVehicleRepository)(implicit executionContext: ExecutionContext) extends IParkingService {

  override def insert (vehicleDTO: VehicleDTO): Future[Int] = {
    val vehicle = new Vehicle(0,vehicleDTO.licence,
      vehicleDTO.vehicle_type,vehicleDTO.engine)
    iVehicleRepository.insert(vehicle)
  }

  override def all() : Future[Seq[Vehicle]] ={
    iVehicleRepository.all()
  }
}





