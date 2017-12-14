class EventsClient {

    constructor() {
        this.eventsSource = new EventSource("/sse/resources/beats");
        this.eventsSource.onopen = (e) => console.log(e);
        this.eventsSource.onmessage = ({data}) => console.log(data);
    }

}

new EventsClient();


