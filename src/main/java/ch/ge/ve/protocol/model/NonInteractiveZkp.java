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
import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * A non-interactive ZKP
 */
@SuppressWarnings("unused")
public final class NonInteractiveZkp {
  private final List<BigInteger> t;
  private final List<BigInteger> s;

  @JsonCreator
  public NonInteractiveZkp(@JsonProperty("t") List<BigInteger> t, @JsonProperty("s") List<BigInteger> s) {
    this.t = ImmutableList.copyOf(t);
    this.s = ImmutableList.copyOf(s);
  }

  public List<BigInteger> getT() {
    return t;
  }

  public List<BigInteger> getS() {
    return s;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NonInteractiveZkp that = (NonInteractiveZkp) o;
    return Objects.equals(t, that.t) && Objects.equals(s, that.s);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t, s);
  }

  @Override
  public String toString() {
    return String.format("NonInteractiveZKP{t=%s, s=%s}", t, s);
  }
}
