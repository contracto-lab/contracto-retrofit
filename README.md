# contracto-retrofit

[![Join the chat at https://gitter.im/contracto-lab/contracto-retrofit](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/contracto-lab/contracto-retrofit?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Build Status](https://travis-ci.org/contracto-lab/contracto-retrofit.svg?branch=master)](https://travis-ci.org/contracto-lab/contracto-retrofit)

Contracto is a framework for API end-to-end testing. This repo contains bindings for Retrofit - popular Android (and Java) REST client library.

To run tests, first start http server in the root directory of this project:

    python -m SimpleHTTPServer 13579

and then execute:

    ./gradlew test
