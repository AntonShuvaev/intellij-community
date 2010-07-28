/*
 * Copyright 2000-2010 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.compiler.impl.newApi;

import com.intellij.util.io.DataExternalizer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
* @author nik
*/
public abstract class VirtualFileStateExternalizer<State extends VirtualFilePersistentState> implements DataExternalizer<State> {
  protected abstract void doSave(DataOutput out, State value) throws IOException;

  protected abstract State doRead(DataInput in, long sourceTimestamp) throws IOException;

  @Override
  public final void save(DataOutput out, State value) throws IOException {
    out.writeLong(value.getSourceTimestamp());
    doSave(out, value);
  }

  @Override
  public final State read(DataInput in) throws IOException {
    final long sourceTimestamp = in.readLong();
    return doRead(in, sourceTimestamp);
  }

}
