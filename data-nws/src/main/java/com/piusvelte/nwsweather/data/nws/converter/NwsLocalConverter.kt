package com.piusvelte.nwsweather.data.nws.converter

import com.piusvelte.nwsweather.data.common.model.Coordinate
import com.piusvelte.nwsweather.data.common.model.GeometryPoint
import com.piusvelte.nwsweather.data.points.model.Point
import com.piusvelte.nwsweather.data.points.model.Properties
import com.piusvelte.nwsweather.data.points.model.RelativeLocation
import javax.inject.Inject

internal class NwsLocalConverter @Inject constructor() : LocalConverter {
    override fun convert(point: LocalPoint): Point {
        return Point(
            point.id,
            point.type,
            convertGeometry(point),
            convertProperties(point),
        )
    }

    private fun convertGeometry(point: LocalPoint): GeometryPoint {
        return GeometryPoint(
            point.geometryType,
            convertGeometryCoordinate(point),
        )
    }

    private fun convertGeometryCoordinate(point: LocalPoint): Coordinate {
        return Coordinate(
            point.geometryCoordinateLatitude,
            point.geometryCoordinateLongitude,
        )
    }

    private fun convertProperties(point: LocalPoint): Properties {
        return Properties(
            point.propertyId,
            point.propertyType,
            point.propertyCwa,
            point.propertyForecastOffice,
            point.propertyGridId,
            point.propertyGridX,
            point.propertyGridY,
            point.propertyForecast,
            point.propertyForecastHourly,
            point.propertyForecastGridData,
            point.propertyObservationStations,
            convertPropertiesRelativeLocation(point),
            point.propertyForecastZone,
            point.propertyCounty,
            point.propertyFireWeatherZone,
            point.propertyTimeZone,
            point.propertyRadarStation,
        )
    }

    private fun convertPropertiesRelativeLocation(point: LocalPoint): RelativeLocation {
        return RelativeLocation(
            point.propertyRelativeLocationType,
            convertPropertiesRelativeLocationGeometry(point),
        )
    }

    private fun convertPropertiesRelativeLocationGeometry(point: LocalPoint): GeometryPoint {
        return GeometryPoint(
            point.propertyRelativeLocationGeometryType,
            convertPropertiesRelativeLocationGeometryCoordinate(point),
        )
    }

    private fun convertPropertiesRelativeLocationGeometryCoordinate(point: LocalPoint): Coordinate {
        return Coordinate(
            point.geometryCoordinateLatitude,
            point.geometryCoordinateLongitude,
        )
    }
}
