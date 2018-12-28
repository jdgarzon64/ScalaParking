package repository


import javax.inject.{Inject, Singleton}
import models.Vehicle
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.Result
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
@Singleton
class VehicleRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] with IVehicleRepository {

  import profile.api._

  private val Vehicles = TableQuery[VehiclesTable]

  override def all() : Future[Seq[Vehicle]] = db.run(Vehicles.result)

  override def insert(vehicle: Vehicle): Future[Int] = {
    db.run(Vehicles += vehicle)
  }

  //.map { _ => () }

  private class VehiclesTable(tag: Tag) extends Table[Vehicle](tag, "Vehicle") {

    def id_vehicle = column[Int]("id_vehicle", O.PrimaryKey)
    def licence = column[String]("licence")
    def vehicle_type = column[String]("vehicle_type")
    def engine = column[String]("engine")

    def * = (id_vehicle,licence,vehicle_type,engine) <>
      ( (Vehicle.apply _).tupled, Vehicle.unapply)
  }

  def delete(idVehicle: Int)= {
    val query = Vehicles.filter(_.id_vehicle === idVehicle)
    val action = query.delete
    db.run(action)
    val sql = action.statements.head

  }
}