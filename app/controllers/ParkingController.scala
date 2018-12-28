package controllers


import business.IParkingService
import dtos.VehicleDTO
import models.Vehicle
import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ParkingController @Inject()
(cc: ControllerComponents, iParkingService: IParkingService)
(implicit executionContext: ExecutionContext)
  extends AbstractController(cc) {

  def add() = Action.async(parse.json[VehicleDTO]) { request =>
    insertParking(request.body)
  }

  private def insertParking(vehicle: VehicleDTO): Future[Result] = {
    iParkingService.insert(vehicle)
      .map(s => Ok(Json.toJson(s)))
      .recoverWith {
        case error: Exception => {
          Future.successful( InternalServerError(error.getMessage) )
        }
      }
  }

  def all() = Action.async {implicit request: Request[AnyContent] =>
    // val vehiclesList = getAllVehicles()
    val vehiclesList : Future[Seq[Vehicle]] = iParkingService.all()
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
      iParkingService.all().map(_ => Ok ("")).recoverWith{
        case error: Exception => {
          Future.successful( InternalServerError(error.getMessage) )
          Future.successful( InternalServerError(error.getMessage) )
        }
      }
    }
*/
}
