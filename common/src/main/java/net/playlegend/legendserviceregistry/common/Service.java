/*
 * This file is part of the LegendServiceRegistry distribution (https://github.com/playlegend/LegendServiceRegistry).
 * Copyright (c) 2020 Minecraft Legend Development
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package net.playlegend.legendserviceregistry.common;

/**
 * This interface represents a {@link Service} from our {@link ServiceRegistry}.
 */
public interface Service {

  /**
   * Check with this method wether the selected service is available or not.
   *
   * @return true when it is available.
   */
  default boolean isAvailable() {
    return true;
  }

}
