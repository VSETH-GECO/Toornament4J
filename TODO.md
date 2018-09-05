## Authorization
- [x] Client Credentials Flow
- [ ] Authorization Flow

## Other
- [ ] Caching

## Endpoints

### Simple Access
#### Viewer
- [x] GET   /disciplines
- [x] GET   /disciplines/{id}
- [x] GET   /tournaments/featured
- [x] GET   /tournaments/{id}
- [ ] GET   /tournaments/{tournament_id}/custom-fields
- [ ] GET   /tournaments/{tournament_id}/participants
- [ ] GET   /tournaments/{tournament_id}/participants/{id}
- [ ] GET   /tournaments/{tournament_id}/stages
- [ ] GET   /tournaments/{tournament_id}/stages/{id}
- [ ] GET   /tournaments/{tournament_id}/groups
- [ ] GET   /tournaments/{tournament_id}/groups/{id}
- [ ] GET   /tournaments/{tournament_id}/rounds
- [ ] GET   /tournaments/{tournament_id}/rounds/{id}
- [ ] GET   /tournaments/{tournament_id}/matches
- [ ] GET   /tournaments/{tournament_id}/matches/{id}
- [ ] GET   /disciplines/{discipline_id}/matches
- [ ] GET   /tournaments/{tournament_id}/matches/{match_id}/games/{number}
- [ ] GET   /tournaments/{tournament_id}/stages/{stage_id}/bracket-nodes
- [ ] GET   /tournaments/{tournament_id}/stages/{stage_id}/ranking-items
- [ ] GET   /tournaments/{tournament_id}/streams
- [ ] GET   /tournaments/{tournament_id}/videos
- [ ] GET   /tournaments/{tournament_id}/matches/{match_id}/videos
- [ ] GET   /playlist/{id}
- [ ] GET   /playlist/{id}/tournaments

#### Participant
- [ ] GET   /tournaments/{id}
- [ ] GET   /tournaments/{id}/custom-fields

### Authorized Access
#### Account
- [ ] GET   /me/info

#### Participant
- [ ] GET   /me/registrations
- [ ] POST  /me/registrations
- [ ] GET   /me/registrations/{id}
- [ ] GET   /me/participants
- [ ] GET   /me/participants/{id}
- [ ] PATCH /me/participants/{id}

#### Organizer
- [ ] GET       /tournaments
- [ ] POST      /tournaments
- [ ] GET       /tournaments/{id}
- [ ] PATCH     /tournaments/{id}
- [ ] DELETE    /tournaments/{id}