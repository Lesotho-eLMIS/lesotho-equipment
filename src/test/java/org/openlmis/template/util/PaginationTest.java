/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2017 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details. You should have received a copy of
 * the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org.
 */

package org.elmis.equipment.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
public class PaginationTest {

  @Test
  public void getPageReturnsTheCorrectPage() {
    int page = 1;
    int size = 3;
    PageRequest pageRequest = PageRequest.of(page, size);

    Page<Integer> pagedList = Pagination.getPage(getList(), pageRequest);

    List<Integer> pagedListContent = pagedList.getContent();

    assertThat(pagedListContent.size()).isEqualTo(3);

    assertThat(pagedListContent.get(0)).isEqualTo(3);
    assertThat(pagedListContent.get(1)).isEqualTo(4);
    assertThat(pagedListContent.get(2)).isEqualTo(5);
  }


  @Test
  public void getPageReturnsEmptyResultIfSpecifiedPageNumberIsOutOfBounds() {
    int page = Integer.MAX_VALUE;
    int size = 5;
    PageRequest pageRequest = PageRequest.of(page, size);

    Page<Integer> pagedList = Pagination.getPage(getList(), pageRequest);

    List<Integer> pagedListContent = pagedList.getContent();
    assertThat(pagedListContent.size()).isEqualTo(0);
  }


  @Test
  public void getPageReturnsAllValuesEvenWhenSizeIsOutOfBounds() {
    int page = 0;
    int size = Integer.MAX_VALUE;
    PageRequest pageRequest = PageRequest.of(page, size);

    Page<Integer> pagedList = Pagination.getPage(getList(), pageRequest);

    List<Integer> pagedListContent = pagedList.getContent();
    assertThat(pagedListContent.size()).isEqualTo(getList().size());
  }

  @Test
  public void getPageReturnsSomeValuesEvenWhenSizeIsOutOfBounds() {
    int page = 1;
    int size = 7;
    PageRequest pageRequest = PageRequest.of(page, size);

    Page<Integer> pagedList = Pagination.getPage(getList(), pageRequest);

    List<Integer> pagedListContent = pagedList.getContent();

    assertThat(pagedListContent.size()).isEqualTo(3);

    assertThat(pagedListContent.get(0)).isEqualTo(7);
    assertThat(pagedListContent.get(1)).isEqualTo(8);
    assertThat(pagedListContent.get(2)).isEqualTo(9);
  }

  private List<Integer> getList() {
    return IntStream
        .range(0, 10)
        .boxed()
        .collect(Collectors.toList());
  }

}
