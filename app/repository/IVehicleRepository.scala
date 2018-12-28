package repository


import com.google.inject.ImplementedBy
import models.Vehicle

import scala.concurrent.Future

@ImplementedBy(classOf[VehicleRepository])
trait  IVehicleRepository {
  // public Parking saveVehicle(InputDTO object) throws ParkingException;
  // public Payment generatePayment(InputDTO object) throws ParkingException;
  // public List<Parking> getAllParkings();

  def insert (vehicle: Vehicle): Future[Int]
  def all() : Future[Seq[Vehicle]]
}
