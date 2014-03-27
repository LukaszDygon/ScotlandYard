# This file is generated by gyp; do not edit.

TOOLSET := target
TARGET := sqlite3
DEFS_Debug := \
	'-D_LARGEFILE_SOURCE' \
	'-D_FILE_OFFSET_BITS=64' \
	'-D_REENTRANT=1' \
	'-DSQLITE_THREADSAFE=1' \
	'-DSQLITE_ENABLE_FTS3' \
	'-DSQLITE_ENABLE_RTREE' \
	'-DDEBUG' \
	'-D_DEBUG'

# Flags passed to all source files.
CFLAGS_Debug := \
	-fPIC \
	-Wall \
	-Wextra \
	-Wno-unused-parameter \
	-pthread \
	-m64 \
	-g \
	-O0

# Flags passed to only C files.
CFLAGS_C_Debug :=

# Flags passed to only C++ files.
CFLAGS_CC_Debug := \
	-fno-rtti \
	-fno-exceptions \
	-Wno-unused-value \
	-include ../src/gcc-preinclude.h

INCS_Debug := \
	-I/home/staff/ian/linux/.node-gyp/0.10.24/src \
	-I/home/staff/ian/linux/.node-gyp/0.10.24/deps/uv/include \
	-I/home/staff/ian/linux/.node-gyp/0.10.24/deps/v8/include \
	-I$(obj)/gen/sqlite-autoconf-3080200

DEFS_Release := \
	'-D_LARGEFILE_SOURCE' \
	'-D_FILE_OFFSET_BITS=64' \
	'-D_REENTRANT=1' \
	'-DSQLITE_THREADSAFE=1' \
	'-DSQLITE_ENABLE_FTS3' \
	'-DSQLITE_ENABLE_RTREE' \
	'-DNDEBUG'

# Flags passed to all source files.
CFLAGS_Release := \
	-fPIC \
	-Wall \
	-Wextra \
	-Wno-unused-parameter \
	-pthread \
	-m64 \
	-O2 \
	-fno-strict-aliasing \
	-fno-tree-vrp \
	-fno-tree-sink \
	-fno-omit-frame-pointer

# Flags passed to only C files.
CFLAGS_C_Release :=

# Flags passed to only C++ files.
CFLAGS_CC_Release := \
	-fno-rtti \
	-fno-exceptions \
	-Wno-unused-value \
	-include ../src/gcc-preinclude.h

INCS_Release := \
	-I/home/staff/ian/linux/.node-gyp/0.10.24/src \
	-I/home/staff/ian/linux/.node-gyp/0.10.24/deps/uv/include \
	-I/home/staff/ian/linux/.node-gyp/0.10.24/deps/v8/include \
	-I$(obj)/gen/sqlite-autoconf-3080200

OBJS := \
	$(obj).target/$(TARGET)/gen/sqlite-autoconf-3080200/sqlite3.o

# Add to the list of files we specially track dependencies for.
all_deps += $(OBJS)

# Make sure our dependencies are built before any of us.
$(OBJS): | $(obj).target/deps/action_before_build.stamp

# CFLAGS et al overrides must be target-local.
# See "Target-specific Variable Values" in the GNU Make manual.
$(OBJS): TOOLSET := $(TOOLSET)
$(OBJS): GYP_CFLAGS := $(DEFS_$(BUILDTYPE)) $(INCS_$(BUILDTYPE))  $(CFLAGS_$(BUILDTYPE)) $(CFLAGS_C_$(BUILDTYPE))
$(OBJS): GYP_CXXFLAGS := $(DEFS_$(BUILDTYPE)) $(INCS_$(BUILDTYPE))  $(CFLAGS_$(BUILDTYPE)) $(CFLAGS_CC_$(BUILDTYPE))

# Suffix rules, putting all outputs into $(obj).

$(obj).$(TOOLSET)/$(TARGET)/%.o: $(srcdir)/%.c FORCE_DO_CMD
	@$(call do_cmd,cc,1)

# Try building from generated source, too.

$(obj).$(TOOLSET)/$(TARGET)/%.o: $(obj).$(TOOLSET)/%.c FORCE_DO_CMD
	@$(call do_cmd,cc,1)

$(obj).$(TOOLSET)/$(TARGET)/%.o: $(obj)/%.c FORCE_DO_CMD
	@$(call do_cmd,cc,1)

# End of this set of suffix rules
### Rules for final target.
LDFLAGS_Debug := \
	-pthread \
	-rdynamic \
	-m64

LDFLAGS_Release := \
	-pthread \
	-rdynamic \
	-m64

LIBS :=

$(obj).target/deps/sqlite3.a: GYP_LDFLAGS := $(LDFLAGS_$(BUILDTYPE))
$(obj).target/deps/sqlite3.a: LIBS := $(LIBS)
$(obj).target/deps/sqlite3.a: TOOLSET := $(TOOLSET)
$(obj).target/deps/sqlite3.a: $(OBJS) FORCE_DO_CMD
	$(call do_cmd,alink)

all_deps += $(obj).target/deps/sqlite3.a
# Add target alias
.PHONY: sqlite3
sqlite3: $(obj).target/deps/sqlite3.a

# Add target alias to "all" target.
.PHONY: all
all: sqlite3

# Add target alias
.PHONY: sqlite3
sqlite3: $(builddir)/sqlite3.a

# Copy this to the static library output path.
$(builddir)/sqlite3.a: TOOLSET := $(TOOLSET)
$(builddir)/sqlite3.a: $(obj).target/deps/sqlite3.a FORCE_DO_CMD
	$(call do_cmd,copy)

all_deps += $(builddir)/sqlite3.a
# Short alias for building this static library.
.PHONY: sqlite3.a
sqlite3.a: $(obj).target/deps/sqlite3.a $(builddir)/sqlite3.a

# Add static library to "all" target.
.PHONY: all
all: $(builddir)/sqlite3.a
