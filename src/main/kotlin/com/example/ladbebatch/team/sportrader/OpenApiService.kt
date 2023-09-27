package com.example.ladbebatch.team.sportrader

import com.example.ladbebatch.common.engine.webclient.client.WebClientTemplate
import com.example.ladbebatch.team.model.Team
import com.example.ladbebatch.team.dto.teams.Teams
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Service
class OpenApiService(
    @Value("\${open-api.teams}") val apiUrl: String,
    @Value("\${open-api.nba-key}") val apiKey: String,
) : WebClientTemplate() {

    fun getAllTeams(): MutableList<Team> {
        val uri: URI = UriComponentsBuilder
            .fromUriString(apiUrl)
            .queryParam("api_key", apiKey).encode().build().toUri()
        val externalData = get<Teams>(uri.toString())

        val teamDto = mutableListOf<Team>()

        externalData.conferences.map { conference ->
            conference.divisions.map { division ->
                division.teams.map { team ->
                    teamDto.add(
                        Team(
                            teamId = team.id,
                            name = team.name,
                            market = team.market,
                            fullName = "${team.name} ${team.market}",
                            reference = team.reference,
                            alias = team.alias,
                            srId = team.sr_id,
                        )
                    )
                }
            }
        }

        return teamDto
    }

}
