package com.piusvelte.nwsweather.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points")
data class PointEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "geometry_type") val geometryType: String,
    @ColumnInfo(name = "geometry_coordinate_latitude") val geometryCoordinateLatitude: Float,
    @ColumnInfo(name = "geometry_coordinate_longitude") val geometryCoordinateLongitude: Float,
    @ColumnInfo(name = "property_id") val propertyId: String,
    @ColumnInfo(name = "property_type") val propertyType: String,
    @ColumnInfo(name = "property_cwa") val propertyCwa: String,
    @ColumnInfo(name = "property_forecast_office") val propertyForecastOffice: String,
    @ColumnInfo(name = "property_grid_id") val propertyGridId: String,
    @ColumnInfo(name = "property_grid_x") val propertyGridX: Int,
    @ColumnInfo(name = "property_grid_y") val propertyGridY: Int,
    @ColumnInfo(name = "property_forecast") val propertyForecast: String,
    @ColumnInfo(name = "property_forecast_hourly") val propertyForecastHourly: String,
    @ColumnInfo(name = "property_forecast_grid_data") val propertyForecastGridData: String,
    @ColumnInfo(name = "property_observation_stations") val propertyObservationStations: String,
    @ColumnInfo(name = "property_relative_location_type") val propertyRelativeLocationType: String,
    @ColumnInfo(name = "property_relative_location_geometry_type") val propertyRelativeLocationGeometryType: String,
    @ColumnInfo(name = "property_relative_location_geometry_coordinate_latitude") val propertyRelativeLocationGeometryCoordinateLatitude: Float,
    @ColumnInfo(name = "property_relative_location_geometry_coordinate_longitude") val propertyRelativeLocationGeometryCoordinateLongitude: Float,
    @ColumnInfo(name = "property_forecast_zone") val propertyForecastZone: String,
    @ColumnInfo(name = "property_county") val propertyCounty: String,
    @ColumnInfo(name = "property_fire_weather_zone") val propertyFireWeatherZone: String,
    @ColumnInfo(name = "property_time_zone") val propertyTimeZone: String,
    @ColumnInfo(name = "property_radar_station") val propertyRadarStation: String,
)
