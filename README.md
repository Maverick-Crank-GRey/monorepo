# monorepo

Playing around with monorepo setup and tools to see how it works for my personal playground

[![Build Status](https://travis-ci.org/jmeagher/monorepo.svg?branch=master)](https://travis-ci.org/jmeagher/monorepo) [![Join the chat at https://gitter.im/jmeagher-monorepo/Lobby](https://badges.gitter.im/jmeagher-monorepo/Lobby.svg)](https://gitter.im/jmeagher-monorepo/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This might be useful to someone out there of how to setup a monorepo using [Bazel](https://github.com/bazelbuild/bazel) and a variety of languages. To get started [install Bazel](https://docs.bazel.build/). `bazel build //...` will build everything. `bazel test //...` will run all the unit tests. [`./test_e2e.sh`](test_e2e.sh) will do a little extra validation of the compiled binaries. For more detail on the build setup see [the Travis-ci configuration](.travis.yml).