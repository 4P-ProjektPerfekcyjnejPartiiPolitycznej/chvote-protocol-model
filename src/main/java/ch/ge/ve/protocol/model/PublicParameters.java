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

import static ch.ge.ve.protocol.util.MoreMath.log2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * The model class grouping all public parameters
 */
@SuppressWarnings("unused")
public final class PublicParameters implements Serializable {
  public static final BigInteger TWO = BigInteger.valueOf(2);
  private final SecurityParameters  securityParameters;
  private final EncryptionGroup     encryptionGroup;
  private final IdentificationGroup identificationGroup;
  private final PrimeField          primeField;
  /**
   * Upper bound of secret voting credential x
   */
  private final BigInteger          q_hat_x;
  /**
   * Alphabet used for voting code
   */
  private final List<Character>     upper_a_x;
  /**
   * Length of voting code l_x
   */
  private final int                 l_x;
  /**
   * Upper bound of secret confirmation credential y
   */
  private final BigInteger          q_hat_y;
  /**
   * Alphabet used for confirmation code
   */
  private final List<Character>     upper_a_y;
  /**
   * Length of confirmation code
   */
  private final int                 l_y;
  /**
   * Alphabet used for verification codes
   */
  private final List<Character>     upper_a_r;
  /**
   * Length of verification codes
   */
  private final int                 upper_l_r;
  /**
   * Length of verification codes RC_{ij} (characters)
   */
  private final int                 l_r;
  /**
   * Alphabet used for finalization codes
   */
  private final List<Character>     upper_a_f;
  /**
   * Length of finalization code
   */
  private final int                 upper_l_f;
  /**
   * Length of finalization codes FC_i (characters)
   */
  private final int                 l_f;
  /**
   * Length of OT messages
   */
  private final int                 upper_l_m;

  /**
   * Number of authorities
   */
  private final int s;
  /**
   * Maximal number of accepted candidates
   */
  private final int n_max;

  @JsonCreator
  public PublicParameters(
      @JsonProperty("securityParameters") SecurityParameters securityParameters,
      @JsonProperty("encryptionGroup") EncryptionGroup encryptionGroup,
      @JsonProperty("identificationGroup") IdentificationGroup identificationGroup,
      @JsonProperty("primeField") PrimeField primeField,
      @JsonProperty("q_hat_x") BigInteger q_hat_x,
      @JsonProperty("upper_a_x") List<Character> upper_a_x,
      @JsonProperty("q_hat_y") BigInteger q_hat_y,
      @JsonProperty("upper_a_y") List<Character> upper_a_y,
      @JsonProperty("upper_a_r") List<Character> upper_a_r,
      @JsonProperty("upper_l_r") int upper_l_r,
      @JsonProperty("upper_a_f") List<Character> upper_a_f,
      @JsonProperty("upper_l_f") int upper_l_f,
      @JsonProperty("s") int s,
      @JsonProperty("n_max") int n_max) {
    // All tests that only impact properties of a given element are performed locally at the constructor level
    // Preconditions tested here are those that impact a combination of the properties of the lower level elements
    Preconditions.checkArgument(encryptionGroup.getH().compareTo(TWO) >= 0,
                                "h should be greater than or equal to 2");
    Preconditions.checkArgument(2 * securityParameters.getTau() <= identificationGroup.getQ_hat().bitLength(),
                                "2 * tau must be smaller than the bit length of q_hat");
    Preconditions.checkArgument(s >= 1, "There must be at least one authority");
    Preconditions.checkArgument(q_hat_x.bitLength() >= 2 * securityParameters.getTau(), "q_hat_x must be >= 2 * tau");
    Preconditions.checkArgument(q_hat_x.compareTo(identificationGroup.getQ_hat()) <= 0, "q_hat_x must be <= q_hat");
    Preconditions.checkArgument(upper_a_x.size() >= 2, "|upper_a_x| >= 2");
    Preconditions.checkArgument(q_hat_y.bitLength() >= 2 * securityParameters.getTau(), "q_hat_y must be >= 2 * tau");
    Preconditions.checkArgument(q_hat_y.compareTo(identificationGroup.getQ_hat()) <= 0, "q_hat_y must be <= q_hat");
    Preconditions.checkArgument(upper_a_y.size() >= 2, "|upper_a_y| >= 2");
    Preconditions.checkArgument(n_max >= 2, "n_max >= 2");
    Preconditions.checkArgument(8 * upper_l_r >= Math.log((n_max - 1) / (1.0 - securityParameters.getEpsilon())),
                                "8 * upper_l_r >= log( ( n_max - 1 ) / ( 1 - epsilon) )");
    Preconditions.checkArgument(upper_a_r.size() >= 2, "|upper_a_r| >= 2");
    Preconditions.checkArgument(8 * upper_l_f >= Math.log(1 / (1.0 - securityParameters.getEpsilon())),
                                "8 * upper_l_f >= log( 1 / ( 1 - epsilon) )");
    this.securityParameters = securityParameters;
    this.encryptionGroup = encryptionGroup;
    this.identificationGroup = identificationGroup;
    this.primeField = primeField;
    this.q_hat_x = q_hat_x;
    this.upper_a_x = ImmutableList.copyOf(upper_a_x);
    this.l_x = (int) Math.ceil(q_hat_x.bitLength() / log2(upper_a_x.size()));
    this.q_hat_y = q_hat_y;
    this.upper_a_y = ImmutableList.copyOf(upper_a_y);
    this.l_y = (int) Math.ceil(q_hat_y.bitLength() / log2(upper_a_y.size()));
    this.upper_a_r = ImmutableList.copyOf(upper_a_r);
    this.upper_l_r = upper_l_r;
    this.l_r = (int) Math.ceil((8.0 * upper_l_r) / log2(upper_a_r.size()));
    this.upper_a_f = ImmutableList.copyOf(upper_a_f);
    this.upper_l_f = upper_l_f;
    this.l_f = (int) Math.ceil((8.0 * upper_l_f) / log2(upper_a_f.size()));
    this.upper_l_m = 2 * ((int) Math.ceil(primeField.getP_prime().bitLength() / 8.0));
    this.s = s;
    this.n_max = n_max;
  }

  /**
   * Run further precondition checks that are CPU intensive. Use with care so that to not degrade the performances.
   * Those preconditions should be run when a component (bulletin board, control component) receives the public
   * parameters.
   */
  public void runIntensivePreconditions() {
    encryptionGroup.runIntensivePreconditions();
  }

  public SecurityParameters getSecurityParameters() {
    return securityParameters;
  }

  public EncryptionGroup getEncryptionGroup() {
    return encryptionGroup;
  }

  public IdentificationGroup getIdentificationGroup() {
    return identificationGroup;
  }

  public PrimeField getPrimeField() {
    return primeField;
  }

  public BigInteger getQ_hat_x() {
    return q_hat_x;
  }

  public List<Character> getUpper_a_x() {
    return upper_a_x;
  }

  @JsonIgnore
  public int getL_x() {
    return l_x;
  }

  public BigInteger getQ_hat_y() {
    return q_hat_y;
  }

  public List<Character> getUpper_a_y() {
    return upper_a_y;
  }

  @JsonIgnore
  public int getL_y() {
    return l_y;
  }

  public List<Character> getUpper_a_r() {
    return upper_a_r;
  }

  public int getUpper_l_r() {
    return upper_l_r;
  }

  @JsonIgnore
  public int getL_r() {
    return l_r;
  }

  public List<Character> getUpper_a_f() {
    return upper_a_f;
  }

  public int getUpper_l_f() {
    return upper_l_f;
  }

  @JsonIgnore
  public int getL_f() {
    return l_f;
  }

  @JsonIgnore
  public int getUpper_l_m() {
    return upper_l_m;
  }

  public int getS() {
    return s;
  }

  public int getN_max() {
    return n_max;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicParameters that = (PublicParameters) o;
    return l_x == that.l_x
        && l_y == that.l_y
        && upper_l_r == that.upper_l_r
        && l_r == that.l_r
        && upper_l_f == that.upper_l_f
        && l_f == that.l_f
        && upper_l_m == that.upper_l_m
        && s == that.s
        && n_max == that.n_max
        && Objects.equals(securityParameters, that.securityParameters)
        && Objects.equals(encryptionGroup, that.encryptionGroup)
        && Objects.equals(identificationGroup, that.identificationGroup)
        && Objects.equals(primeField, that.primeField)
        && Objects.equals(q_hat_x, that.q_hat_x)
        && Objects.equals(upper_a_x, that.upper_a_x)
        && Objects.equals(q_hat_y, that.q_hat_y)
        && Objects.equals(upper_a_y, that.upper_a_y)
        && Objects.equals(upper_a_r, that.upper_a_r)
        && Objects.equals(upper_a_f, that.upper_a_f);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(securityParameters, encryptionGroup, identificationGroup, primeField, q_hat_x, upper_a_x, l_x, q_hat_y,
              upper_a_y, l_y, upper_a_r, upper_l_r, l_r, upper_a_f, upper_l_f, l_f, upper_l_m, s, n_max);
  }
}
