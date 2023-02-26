package com.example.ladbebatch.team.chunk.reader

import com.example.ladbebatch.common.RestTemplateClient
import com.example.ladbebatch.team.dto.team.TeamsApi
import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI


@Component
class SportradarTeamReader(
    @Value("\${open-api.teams}") val apiUrl: String,
    @Value("\${open-api.nba-key}") val apiKey: String,
    @Value("#{jobParameters['year']}") val year: String,
    @Value("#{jobParameters['season']}") val season: String,
) : RestTemplateClient(), ItemReader<TeamsApi> {

    override fun read(): TeamsApi {
        val uri: URI = UriComponentsBuilder
            .fromUriString("$apiUrl/$year/$season/rankings.json")
            .queryParam("api_key", apiKey).encode().build().toUri()
        return getData<TeamsApi>(uri.toString())
    }

}
