// Copyright 2023 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.packages;

import com.google.common.collect.ImmutableMap;

/**
 * A library of the fixed Starlark environment for various contexts.
 *
 * <p>This is the source of truth for what symbols are available in what Starlark contexts (BUILD,
 * .bzl, etc.), before considering how symbols may be added by registering them on the rule class
 * provider, or how symbols may be substituted by builtins injection. In other words, this is the
 * starting point for defining the minimum Starlark environments that Bazel supports for BUILD
 * files, .bzl files, etc. See {@link BazelStarlarkEnvironment} for the final determination of the
 * environment after accounting for registered symbols and builtins injection.
 *
 * <p>This is split between an interface in the lib/packages/ directory and an implementation in the
 * lib/analysis/starlark/ directory, in order to avoid new dependency edges from lib/packages/ to
 * lib/analysis/.
 */
public interface StarlarkGlobals {

  /**
   * Returns the fixed top-levels for BUILD files that also happen to be fields of {@code native}.
   * This does not include any native rules.
   */
  ImmutableMap<String, Object> getFixedBuildFileToplevelsSharedWithNative();

  /** Returns the fixed top-levels for BUILD files that are *not* also fields of {@code native}. */
  ImmutableMap<String, Object> getFixedBuildFileToplevelsNotInNative();

  /** Returns the fixed top-levels for .bzl files, excluding the {@code native} object. */
  ImmutableMap<String, Object> getFixedBzlToplevels();
}
