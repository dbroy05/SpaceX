package com.spacex.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Launch {

    @SerializedName("flight_number")
    var flightNumber: String? = null

    @SerializedName("details")
    var details: String? = null

    @SerializedName("mission_name")
    var missionName: String? = null

    @SerializedName("rocket")
    var rocket : Rocket? = null

    class Rocket {
        @SerializedName("rocket_name")
        var rocketName: String? = null
        @SerializedName("rocket_type")
        var rocketType: String? = null

        @SerializedName("first_stage")
        var firstStage: FirstStage? = null

        @SerializedName("second_stage")
        var secondStage: SecondStage? = null

    }

    class SecondStage {
        @SerializedName("payloads")
        var payloads: Array<Payloads>? = null

    }

    class Payloads {
        @SerializedName("nationality")
        var nationality: String? = null

        @SerializedName("manufacturer")
        var manufacturer: String? = null
    }

    class FirstStage {
        @SerializedName("cores")
        var cores: Array<Cores>? = null
    }

    class Cores {
        @SerializedName("core_serial")
        var coreSerial: String? = null
    }

    @SerializedName("launch_site")
    var launchSite : LaunchSite? = null

    class LaunchSite {
        @SerializedName("site_name")
        var siteName: String? = null
    }




    @SerializedName("links")
    var links: Link? = null

    class Link {
        @SerializedName("mission_patch_small")
        var missionPatchSmall : String? = null
        @SerializedName("mission_patch")
        var missionPatch : String? = null
        @SerializedName("article_link")
        var articleLink : String? = null
        @SerializedName("wikipedia")
        var wikipedia : String? = null
        @SerializedName("video_link")
        var videoLink : String? = null
    }

    @SerializedName("launch_date_utc")
    var launchDate: Date? = null

}
