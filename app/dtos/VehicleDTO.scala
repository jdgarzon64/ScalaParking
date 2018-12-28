package dtos

import play.api.libs.json.Json

case class
VehicleDTO(licence: String,vehicle_type: String ,engine: String)

object VehicleDTO {
  implicit val vehicleFormat = Json.format[VehicleDTO]
}
