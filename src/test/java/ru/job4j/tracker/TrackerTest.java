package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackerTest {
    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        assertThat(tracker.findAll().get(0).getName(), is(bug.getName()));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("bug"));
        tracker.add(new Item("name"));
        Item item = tracker.add(new Item("first"));
        assertThat(tracker.findByName("first").get(0).getId(), is(item.getId()));
    }

    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is(bugWithDesc.getName()));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteMock() {
        Tracker tracker = new Tracker();
        Item item = mock(Item.class);

        when(item.getId()).thenReturn(1);

        tracker.add(item);
        int id = item.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void  findByIdMock() {
        Tracker tracker = new Tracker();
        Item item = mock(Item.class);

        when(item.getId()).thenReturn(1);

        tracker.add(item);
        int id = item.getId();
        assertThat(id, is(1));
    }

    @Test
    public void  findByNameMock() {
        Tracker tracker = new Tracker();
        Item item = mock(Item.class);

        when(item.getName()).thenReturn("Sergey");

        tracker.add(item);
        String name = item.getName();
        assertThat(name, is("Sergey"));
    }

}