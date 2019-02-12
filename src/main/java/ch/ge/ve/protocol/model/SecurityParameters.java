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
import java.io.Serializable;
import java.util.Objects;

/**
 * This class defines the security parameters to be used
 */
@SuppressWarnings("unused")
public final class SecurityParameters implements Serializable {
  /**
   * Minimal privacy security level &sigma;
   */
  private final int sigma;

  /**
   * Minimal integrity security level &tau;
   */
  private final int tau;

  /**
   * Output length of collision-resistant hash-function upper_l (in bytes)
   */
  private final int upper_l;

  /**
   * Deterrence factor &epsilon; (with 0 &lt; &epsilon; &le; 1)
   */
  private final double epsilon;

  @JsonCreator
  public SecurityParameters(
      @JsonProperty("sigma") int sigma,
      @JsonProperty("tau") int tau,
      @JsonProperty("upper_l") int upper_l,
      @JsonProperty("epsilon") double epsilon) {
    Preconditions.checkArgument(upper_l >= Math.max(sigma, tau) / 4.0);
    Preconditions.checkArgument(0.0 < epsilon && epsilon <= 1.0);
    this.sigma = sigma;
    this.tau = tau;
    this.upper_l = upper_l;
    this.epsilon = epsilon;
  }

  public int getSigma() {
    return sigma;
  }

  public int getTau() {
    return tau;
  }

  public int getUpper_l() {
    return upper_l;
  }

  public double getEpsilon() {
    return epsilon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SecurityParameters that = (SecurityParameters) o;
    return sigma == that.sigma
        && tau == that.tau
        && upper_l == that.upper_l
        && Double.compare(that.epsilon, epsilon) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(sigma, tau, upper_l, epsilon);
  }
}
