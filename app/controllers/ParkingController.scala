package controllers


import dtos.VehicleDTO
import models.Vehicle
import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import repository.VehicleRepository

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ParkingController @Inject()
(cc: ControllerComponents, vehicleRepository: VehicleRepository)
(implicit executionContext: ExecutionContext)
  extends AbstractController(cc) {

  def add() = Action.async(parse.json[VehicleDTO]) { request =>
    insertParking(request.body)
  }

  private def insertParking(vehicle: VehicleDTO): Future[Result] = {
    vehicleRepository.insert(vehicle)
      .map(_ => Ok(""))
      .recoverWith {
        case error: Exception => {
          Future.successful( InternalServerError(error.getMessage) )
        }
      }
  }

  def all() = Action.async {implicit request: Request[AnyContent] =>
    // val vehiclesList = getAllVehicles()
    val vehiclesList : Future[Seq[Vehicle]] = vehicleRepository.all()
    vehiclesList.map(s => Ok(Json.toJson(s) ))
  }
/*
  def delete = Action.async {request =>
    console.log(request.body)

  }
*/
  /*
  def deleteVehicle(idVehicle: Int): Future[Result] = {
    vehicleRepository.delete(idVehicle)
      .map(_ => Ok(""))
      .recoverWith {
        case error: Exception => {
          Future.successful( InternalServerError(error.getMessage) )
        }
      }
  }
*/

  /*
    private def getAllVehicles(): Future[Seq[Vehicle]] = {
      vehicleRepository.all().map(_ => Ok ("")).recoverWith{
        case error: Exception => {
          Future.successful( InternalServerError(error.getMessage) )
          Future.successful( InternalServerError(error.getMessage) )
        }
      }
    }
    */
}
