package business

import com.google.inject.ImplementedBy
import dtos.VehicleDTO
import javax.inject.Singleton
import models.Vehicle
import play.api.mvc.Result

import scala.concurrent.Future

@ImplementedBy(classOf[ParkingService])
trait IParkingService {
  def insert (vehicleDTO: VehicleDTO): Future[Int]
  def all() : Future[Seq[Vehicle]]
}
