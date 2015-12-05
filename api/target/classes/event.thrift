namespace java com.boffo.event.api

struct ClientInfo {
    1: string apiKey
    2: string clientId
    3: string runtimeEnvironment
    4: string requestId
}

enum ErrorType {
    NOT_FOUND = 1,
    SERVICE_UNAVAILABLE = 2,
    WRONG_STATE = 3
}

exception EventError {
    1: ErrorType type
    2: string info
}

enum RepeatType {
    DAILY = 1,
    WEEKLY = 2,
    MONTHLY = 3,
    YEARLY = 4
}

struct Repeat {
    1: RepeatType type
    2: i64 offset
}

struct OneTimeParams {
    1: i64 start
    2: i64 duration
    3: list<string> attendeeIds = {}
}

struct RepeatParams {
    1: i64 firstStart
    2: i64 duration
    3: Repeat repeat
    4: map<i32, list<string>> attendeeIdsByOccurence = {}
}

struct OngoingParams {
    1: list<string> attendeeIds = {}
}

union EventParams {
    1: RepeatParams repeat
    2: OneTimeParams oneTime
    3: OngoingParams ongoing
}

struct Event {
    1: string eventName
    2: EventParams params
    3: optional map<string, string> context
}

service EventService {
    void create(1: ClientInfo client, 2: string eventId, 3: Event event) throws (1: EventError e)
    void update(1: ClientInfo client, 2: string eventId, 3: Event event) throws (1: EventError e)
    void cancel(1: ClientInfo client, 2: string eventId) throws (1: EventError e)
    Event find(1: ClientInfo client, 2: string eventId) throws (1: EventError e)
}

service AckService {
    void ack(1: ClientInfo client, 2: string eventId) throws (1: EventError e)
}

const string EVENT_SERVICE = "EventService"
const string ACK_SERVICE = "AckService"