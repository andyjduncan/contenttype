###Spring MVC content type negotiation example

Some requests to try:

    Accept: application/vnd.acn.thing+json

    Accept: application/vnd.acn.thing-v1+json

    Accept: application/vnd.acn.thing-v3+json;q=0.7,application/vnd.acn.thing-v2+json;q=0.2,application/vnd.acn.thing-v1+json;q=0.1