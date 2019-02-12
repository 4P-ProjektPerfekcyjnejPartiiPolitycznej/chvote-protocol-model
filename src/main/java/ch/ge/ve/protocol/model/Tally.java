/*-------------------------------------------------------------------------------------------------
 - #%L                                                                                            -
 - protocol-model                                                                                 -
 - %%                                                                                             -
 - Copyright (C) 2016 - 2018 République et Canton de Genève                                       -
 - %%                                                                                             -
 - This program is free software: you can redistribute it and/or modify                           -
 - it under the terms of the GNU Affero General Public License as published by                    -
 - the Free Software Foundation, either version 3 of the License, or                              -
 - (at your option) any later version.                                                            -
 -                                                                                                -
 - This program is distributed in the hope that it will be useful,                                -
 - but WITHOUT ANY WARRANTY; without even the implied warranty of                                 -
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                                   -
 - GNU General Public License for more details.                                                   -
 -                                                                                                -
 - You should have received a copy of the GNU Affero General Public License                       -
 - along with this program. If not, see <http://www.gnu.org/licenses/>.                           -
 - #L%                                                                                            -
 -------------------------------------------------------------------------------------------------*/

package ch.ge.ve.protocol.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Model class containing the tally by counting circle.
 */
@SuppressWarnings("unused")
public final class Tally {
  private final Map<Integer, CountingCircle> countingCirclesById;
  private final Map<Integer, List<Long>>     globalTallyByCountingCircleId;

  public Tally(Map<CountingCircle, List<Long>> globalTally) {
    Preconditions.checkNotNull(globalTally, "globalTally must not be null");

    countingCirclesById = new HashMap<>();
    globalTallyByCountingCircleId = new HashMap<>();

    for (Map.Entry<CountingCircle, List<Long>> entry : globalTally.entrySet()) {
      countingCirclesById.putIfAbsent(entry.getKey().getId(), entry.getKey());
      globalTallyByCountingCircleId.putIfAbsent(entry.getKey().getId(), ImmutableList.copyOf(entry.getValue()));
    }
  }

  @JsonCreator
  public Tally(@JsonProperty("countingCirclesById") Map<Integer, CountingCircle> countingCirclesById,
               @JsonProperty("globalTallyByCountingCircleId") Map<Integer, List<Long>> globalTallyByCountingCircleId) {
    this.countingCirclesById = countingCirclesById;
    this.globalTallyByCountingCircleId = globalTallyByCountingCircleId;
  }

  public Map<Integer, CountingCircle> getCountingCirclesById() {
    return countingCirclesById;
  }

  public Map<Integer, List<Long>> getGlobalTallyByCountingCircleId() {
    return globalTallyByCountingCircleId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tally tally = (Tally) o;
    return Objects.equals(countingCirclesById, tally.countingCirclesById)
        && Objects.equals(globalTallyByCountingCircleId, tally.globalTallyByCountingCircleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countingCirclesById, globalTallyByCountingCircleId);
  }
}
