package models

import play.api.libs.json.Json

case class
Vehicle(id_vehicle: Int,licence: String,vehicle_type: String ,engine: String)

object Vehicle {
  implicit val vehicleFormat = Json.format[Vehicle]
}
